# cryo-em-scripts
some scriptes about cryoem including preprocessing, image selection, extract particle coordinates etc....

## How to use these scripts:

All scripts written in java need to be compile first

#### cleanpick.java
    java cleanpick particle.star

<<<<<<< HEAD
#### cryoSPARCtoRelion.java
    Download the particle.cs file in cryoSPARC
    Run csparc2star.py first to get the star file
=======
###### cryoSPARCtoRelion.java
>>>>>>> 8ffba87b6b0b38baac5dd2f5cb962674e2b22bb3
    java cryoSPARCtoRelion particle_from_relion.star particle_from_cryoSPARC.star output.star

#### def.py
When there is a vertical defect in your canmera, you can remove the particles located there by its coordinates
    python def.py 

