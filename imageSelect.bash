#!/bin/bash

if [ $1 == "good1" ]; then
	mkdir Select/job008
	`which relion_display` --gui --i Select/job006/micrographs_split001.star --allow_save --fn_imgs Select/job008/micrographs.star 
fi

if [ $1 == "good2" ]; then
	mkdir Select/job009
        `which relion_display` --gui --i Select/job006/micrographs_split002.star --allow_save --fn_imgs Select/job009/micrographs.star
fi

if [ $1 == "good3" ]; then
	mkdir Select/job010
        `which relion_display` --gui --i Select/job006/micrographs_split003.star --allow_save --fn_imgs Select/job010/micrographs.star
fi

if [ $1 == "good4" ]; then
	mkdir Select/job011
        `which relion_display` --gui --i Select/job006/micrographs_split004.star --allow_save --fn_imgs Select/job011/micrographs.star
fi

if [ $1 == "bad1" ]; then
	mkdir Select/job012
        `which relion_display` --gui --i Select/job007/micrographs_split001.star --allow_save --fn_imgs Select/job012/micrographs.star
fi

if [ $1 == "bad2" ]; then
	mkdir Select/job013
        `which relion_display` --gui --i Select/job007/micrographs_split002.star --allow_save --fn_imgs Select/job013/micrographs.star
fi

if [ $1 == "bad3" ]; then
	mkdir Select/job014
        `which relion_display` --gui --i Select/job007/micrographs_split003.star --allow_save --fn_imgs Select/job014/micrographs.star
fi

