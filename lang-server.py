from requests import get
from time import time
from urllib import request
from zipfile import ZipFile
from json import dump
from progressbar import ProgressBar

pbar = None

def show_progress(block_num, block_size, total_size):
    global pbar
    if pbar is None:
        pbar = ProgressBar(maxval=total_size)
        pbar.start()

    downloaded = block_num * block_size
    if downloaded < total_size:
        pbar.update(downloaded)
    else:
        pbar.finish()
        pbar = None

def install_from_url(url, asset_name, install_dir):
    r = get(url).json()
    version = r['tag_name']
    download_url = next(asset for asset in r['assets'] if asset['name'] == asset_name)['browser_download_url']
    download_and_extract(download_url, install_dir)
    write_version_file(version, install_dir)

def download_and_extract(url, extract_path):
    zip_path, _ = request.urlretrieve(url, reporthook=show_progress)
    with ZipFile(zip_path, 'r') as f:
        f.extractall(extract_path)

def write_version_file(version, path):
    now = int(time()) 
    data = {
        'version': version,
        'lastUpdate': now
    }
    with open(path+'/SERVER-INFO', 'w') as f:
        dump(data, f)

def install_from_repo(repo, asset_name, install_dir):
    print('Downloading '+repo+'...')
    url = 'https://api.github.com/repos/'+repo+'/releases/latest'
    install_from_url(url, asset_name, install_dir)

if __name__ == "__main__":
    install_from_repo('fwcd/kotlin-language-server', 'server.zip', './fwcd.kotlin/langServerInstall')
    install_from_repo('fwcd/kotlin-debug-adapter', 'adapter.zip', './fwcd.kotlin/debugAdapterInstall')
    with open('./fwcd.kotlin/config.json', 'w') as f:
        dump({'initialized': True}, f)