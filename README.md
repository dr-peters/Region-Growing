# Region-Growing
A region-growing Java project created for my Algorithms &amp; Data Structures class.

This project implements a special case of the region growing algorithm for binary images with pixel values of either 0 or 255. Using the 8-connected neighborhood for pixels adjacent relationship, a set of seed points will be selected based on a user-defined value (0 or 255). Regions will grow from these seed points. The criterion for adding a pixel to a region is checking if an adjacent pixel has the same pixel value (0 or 255). If a pixel has the same intensity as a specified seed point, it is added to that seed's respective region. The process is repeated until no pixels are left.
