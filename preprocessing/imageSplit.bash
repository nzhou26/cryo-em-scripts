#!/bin/bash

scp $1 unfiltered/lowpass/
cd unfiltered/lowpass/
scp /storage_data/zhou_Ningkun/scripts/imageSelecByWarpFromLowpass.java .
javac imageSelecByWarpFromLowpass.java
java imageSelecByWarpFromLowpass $1
rm -r imageSelecByWarpFromLowpass*
cd ../..

mkdir Import/job004
relion_star_loopheader rlnMicrographName > Import/job004/micrographs.star
ls -rt unfiltered/lowpass/selectedByWarp/*.mrc >> Import/job004/micrographs.star

mkdir Import/job005
relion_star_loopheader rlnMicrographName > Import/job005/micrographs.star
ls -rt unfiltered/lowpass/*.mrc >> Import/job005/micrographs.star

mkdir Select
mkdir Select/job006
`which relion_star_handler` --i Import/job004/micrographs.star --o Select/job006/micrographs.star --split  --nr_split 4

mkdir Select/job007
`which relion_star_handler` --i Import/job005/micrographs.star --o Select/job007/micrographs.star --split  --nr_split 3
