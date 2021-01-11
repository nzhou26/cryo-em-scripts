# cryo-em-scripts
Some scriptes about cryoem including preprocessing, image selection, extract particle coordinates etc....

## How to use these scripts:

All scripts written in java need to be compile first

#### clean_pyck.py
    ./clean_py.py particles.star

#### cryoSPARCtoRelion.java
    Download the particle.cs file in cryoSPARC
    Run csparc2star.py first to get the star file

###### cryoSPARCtoRelion.java
    java cryoSPARCtoRelion particle_from_relion.star particle_from_cryoSPARC.star output.star

#### def.py
When there is a vertical defect in your canmera, you can remove the particles located there by its coordinates
    python def.py 

