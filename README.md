# bloomfilter-experiments
The intent of this repository is to document the difference between the twitter algebird bloomfilter and mutable HashSets. We are finding the words in common between Othello and Macbeth. In order to investigate the efficiency of the bloomfilter I will document the number of false positives as well as the elapsed run time of the functions. I will vary the width of the bloomfilter (the optimal number of hashes is m/n*ln(2) and follows from the width). 

| filter type | filter width  | run time(ms) | % of false positives |
| ----------- | ------------- |------------- | -------------------- |
| algebird    |   2*n         | 749          | 27.8 %               |
| algebird    |   5*n         | 1154         | 2.3 %                |
| algebird    |   10*n        | 1410         | 0 %                  | 
| algebird    |   100*n       | 1849         | 0%                   |

Using set intersections I was able to use HashSet() and solve the problem in 1900 ms. 

Next steps involve timing the serialization process as well as scaling up the bloomfilter experiments. 
