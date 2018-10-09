#!/usr/bin/python3
import os

basepath = "src/rs/ac/bg/etf/pp1/ast/"
files = os.listdir(basepath)
for filename in files:
    filepath = os.path.join(basepath, filename)
    f = open(filepath, "r")
    c = f.read()
    f.close()
    c = c.replace("/home/nidzo/projects/mjcomp/src/rs/ac/bg/etf/pp1/ast", "rs.ac.bg.etf.pp1.ast")
    f = open(filepath, "w")
    f.write(c)
    f.close()
