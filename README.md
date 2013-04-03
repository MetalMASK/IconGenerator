IconGenerator
=============

Generating Icons as stimuli for running behavioral experiment

This small project was created for generating sets of icons (by simply overlaying images) in a behavioral experiment setup. The requirement is to overlay two foreground images (ro and lo) on top of a background image. This automated script is for quickly generating a set of icons with ro-lo distance in various different setting.

There are 2 ways to generate a set of icons:
1. RunExGenerator.java, which will create a set of configs (controlling where the ro and lo is, size of them, etc.) and generate a set of corresponding icons.
2. RunExGeneratorFromConfig.java, which will read a config file and parse each line as one config, then generate corresponding set of icons.

The background, ro and lo can use user-selected images; the size will need to be set in code.

The distance between ro and lo can be set from minimal to maximal (with upper bound of the length of the background image); the distance increment is also set by the user.