#!/usr/bin/python3
import os, shutil
HOME = os.environ['HOME']
myOfflineDir = HOME+"/.android/manual-offline-m2/"
cacheDir = HOME+"/.gradle/caches/modules-2"
sources = list()
destinations = list()


diffStr = lambda Str0, Str1: Str0.replace(Str1, "")

def flatten(Dir):
    Dir = diffStr(Dir, cacheDir)
    Dirs = Dir.split("/")
    del Dirs[-2]
    try: pkgsDirs = Dirs[1].split(".")
    except: return HOME+"/trash"
    del Dirs[1]
    for pkgsDir in pkgsDirs[::-1]:
        Dirs.insert(1, pkgsDir)
    newDir = "/".join(Dirs)
    return myOfflineDir + newDir

def copyF(src, dest):
    while True:
        try: shutil.copy(src, dest); break
        except: pass
        os.makedirs('/'.join(dest.split('/')[:-1]) + '/')

for root, dirs, files in os.walk(cacheDir):
    if len(files) > 0:
        for file in files:
            fileDir = root + '/' + file
            sources.append(fileDir)
            pathDir = flatten(fileDir)
            destinations.append(pathDir)


for source, destination in zip(sources, destinations):
    copyF(source, destination)

print("#Done")

