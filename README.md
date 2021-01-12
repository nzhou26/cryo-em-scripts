# cryo-em-scripts
Some simple scriptes about cryoem including preprocessing, image selection, extract particle coordinates etc....

## How to use these scripts:

All scripts written in java need to be compile first

#### clean_pyck.py
This script is written for generating per-micrograph coordinate files that are readable in relion
```sh 
./clean_py.py particles.star 
```

#### cs2relion.py

Download the particle.cs file in cryoSPARC
```sh
csparc2star.py cryosparc_P00_J00_class_00_final_particles.cs fromcs.star
```


```sh
cs2relion.py fromcs.star particles_from_relion.star output.star
```

#### def.py
When there is a vertical defect in your canmera, you can remove the particles located there by its coordinates
    python def.py 

