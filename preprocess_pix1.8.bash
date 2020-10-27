#!/bin/bash
for ((i = 0; i< 1000; i++))
	do

	mkdir preprocessing
	cd preprocessing
	
	mkdir movies
	cd movies
	ln -s ../../*.tif .
	cd ..
	mkdir Import
	mkdir Import/job001

		relion_star_loopheader rlnMicrographMovieName > Import/job001/movies.star
		ls -rt movies/*.tif >> Import/job001/movies.star

	mkdir MotionCorr
	mkdir MotionCorr/job002

		`which relion_run_motioncorr_mpi` --i Import/job001/movies.star --o MotionCorr/job002/ --first_frame_sum 1 --last_frame_sum -1 --use_motioncor2  --motioncor2_exe /opt/bin/MotionCor2 --other_motioncor2_args " -InTiff " --gpu 0:1 --bin_factor 2 --bfactor 150 --angpix 0.9 --voltage 200 --dose_per_frame 2.2 --preexposure 0 --patch_x 5 --patch_y 5 --dose_weighting  --only_do_unfinished

	mkdir CtfFind
	mkdir CtfFind/job003

		`which relion_run_ctffind_mpi` --i MotionCorr/job002/corrected_micrographs.star --o CtfFind/job003/ --CS 2.7 --HT 200 --AmpCnst 0.07 --XMAG 10000 --DStep 1.8 --Box 512 --ResMin 20 --ResMax 3 --dFMin 20000 --dFMax 50000 --FStep 500 --dAst 100 --use_gctf --gctf_exe /opt/bin/Gctf --angpix 1.8 --EPA --gpu "0:1" --only_do_unfinished

	mkdir unfiltered
		cd unfiltered
		ln -s ../MotionCorr/job002/movies/*.mrc .
		bash /storage_data/zhou_Ningkun/scripts/lowpass_on_the_fly_2.bash
		cd ../..
		
echo -e "\n\n==================\nwaiting\n==================\n"
sleep 500
done


