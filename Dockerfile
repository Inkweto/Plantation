FROM python:slim
RUN pip install progressbar requests \
    && mkdir /tmp/fwcd.kotlin
WORKDIR /tmp
COPY lang-server.py lang-server.py
RUN python lang-server.py

FROM gradle:jdk11

ENV SDKMAN_DIR /usr/local/sdkman
ENV KOTLIN_PATH ${SDKMAN_DIR}/candidates/kotlin/current/

# Zip and unzip
RUN apt-get update \
    && apt-get install -y unzip zip \
    && rm -rf /var/lib/apt/lists/*

# SDKMAN
RUN curl -s get.sdkman.io | bash
RUN echo "sdkman_auto_answer=true" > ${SDKMAN_DIR}/etc/config \
    && echo "sdkman_auto_selfupdate=false" >> ${SDKMAN_DIR}/etc/config \
    && echo "sdkman_insecure_ssl=false" >> ${SDKMAN_DIR}/etc/config \
    && chmod a+x "${SDKMAN_DIR}/bin/sdkman-init.sh" 

# Kotlin
RUN ["/bin/bash", "-c", "source ${SDKMAN_DIR}/bin/sdkman-init.sh && sdk install kotlin"]

# Dev user and workspace parameters
ARG USERNAME=gradle
ARG USER_UID=1000
ARG USER_GID=$USER_UID
ARG WORKSPACE=/kotlin

# Language server
RUN mkdir -p /home/${USERNAME}/.vscode-server/data/User/globalStorage
COPY --from=0 /tmp/fwcd.kotlin /home/${USERNAME}/.vscode-server/data/User/globalStorage/fwcd.kotlin

# User uid and gid change
RUN groupmod -g $USER_GID $USERNAME \
    && usermod -u $USER_UID $USERNAME \
    && chown -R ${USER_UID}:${USER_GID} /home/${USERNAME}

# SDKMAN init files and aliases
RUN echo "source ${SDKMAN_DIR}/bin/sdkman-init.sh" >> /home/${USERNAME}/.bashrc \
    && echo "alias kts='java -jar ${KOTLIN_PATH}/lib/kotlin-compiler.jar -script'" >> /home/${USERNAME}/.bashrc

# Workspace
RUN mkdir ${WORKSPACE} && chown -R ${USER_UID}:${USER_GID} ${WORKSPACE}
WORKDIR ${WORKSPACE}

EXPOSE 8080