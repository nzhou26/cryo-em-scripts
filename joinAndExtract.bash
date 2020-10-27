#!/bin/bash
cd MotionCorr/job002/movies/
scp /storage_data/zhou_Ningkun/scripts/imageSelecByRelionFromCtf.java .
javac imageSelecByRelionFromCtf.java
java imageSelecByRelionFromCtf ../../../Select/job008/micrographs.star
java imageSelecByRelionFromCtf ../../../Select/job009/micrographs.star
java imageSelecByRelionFromCtf ../../../Select/job010/micrographs.star
java imageSelecByRelionFromCtf ../../../Select/job011/micrographs.star
java imageSelecByRelionFromCtf ../../../Select/job012/micrographs.star
java imageSelecByRelionFromCtf ../../../Select/job013/micrographs.star
java imageSelecByRelionFromCtf ../../../Select/job014/micrographs.star
rm imageSelecByRelionFromCtf*
cd ../../../../
mkdir goodMRC 
mv preprocessing/MotionCorr/job002/movies/selected/*.mrc goodMRC 
rm -r preprocessing
