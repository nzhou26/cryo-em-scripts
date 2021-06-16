#!/usr/bin/python3
import starfile 
import pandas as pd
import matplotlib.pyplot as plt
import sys

if len(sys.argv) != 3:
    print("usage: ./relion_sd.py particles_after_ctf_refine.star particles_extract.star")
    exit()
df = starfile.open(sys.argv[1])
df_old = starfile.open(sys.argv[2])
mrcNames = df["rlnMicrographName"]
temp = {val : key for key, val in mrcNames.items()}
names_non_dup = {val : key for key, val in temp.items()}

mrc_list = names_non_dup.values()


dus = []
dvs = []
dur = []
dvr = []
num_p = []
ls = []
idx = []

for item in mrc_list:
    select = df.loc[df['rlnMicrographName'] == item]
    du_std = select['rlnDefocusU'].std()
    du_range = select['rlnDefocusU'].max() - select['rlnDefocusU'].min()
    dv_std = select['rlnDefocusV'].std()
    dv_range = select['rlnDefocusV'].max() - select['rlnDefocusV'].min()
    num_parts = len(select.index)

    select_old = df_old.loc[df_old['rlnMicrographName'] ==item ]
    num_parts_old = len(select_old.index)

    loss = 1000 * (num_parts_old - num_parts) / num_parts_old
    dus.append(du_std)
    dvs.append(dv_std)
    dur.append(du_range)
    dvr.append(dv_range)
    num_p.append(num_parts)
    ls.append(loss)
    idx.append(item)

new_df = pd.DataFrame({"DefocusU_std" : dus,
                        "DefocusV_std" : dvs,
                        "DefocusU_range":dur,
                        "DefocusV_range":dvr,
                        "Number_of_Particles":num_p,
                        "Loss_of_particles": ls}, index=[idx])
new_df.plot()
print(new_df.corr())
plt.show()
