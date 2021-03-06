# bloomfilter-experiments
The intent of this repository is to document the difference between the twitter algebird bloomfilter and mutable HashSets. We are finding the words in common between Othello and Macbeth. In order to investigate the efficiency of the bloomfilter I will document the number of false positives as well as the elapsed run time of the functions. I will vary the width of the bloomfilter (the optimal number of hashes is m/n*ln(2) and follows from the width). 

| filter type | filter width  | run time(ms) | % of false positives |
| ----------- | ------------- |------------- | -------------------- |
| algebird    |   2*n         | 749          | 27.8 %               |
| algebird    |   5*n         | 1154         | 2.3 %                |
| algebird    |   10*n        | 1410         | 0 %                  | 
| algebird    |   100*n       | 1849         | 0%                   |

Using set intersections I was able to use HashSet() and solve the problem in 1900 ms. 

An important distinction to make is that when I used algebird, I only created a bloomfilter for Macbeth, whereas I created a hashset for both plays in order to take the intersection. To remedy this, I created a second implementation of hashsets in which I hashed only Macbeth and looked elementwise for the words of Othello. In this case the problem was solved in 1825ms, around the same time as the first implementation. Something to look in to is the asymptotic run time, but from the appearance of the experiments, especially if a few false positives are tolerable, the algebird implementation is faster. 

## Size vs. Accuracy tradeoffs 
The issue with bloomfilters as we stand right now is that to obtain 3 decimal points of accuracy, we need 13 bits of array space per item. The formula that governs bits of array space per accuracy is:

m/n = -ln(p)/ln(2)^2

There are a lot of theoretical bloom filters that could offer some aid in improving accuracy:storage space, however very few have actually been implemented in a way that would benefit this. Hadoop does have a dynamic bloom filter implemented, and all this does is allow you to have a much smaller bloom filter and add more objects/increase bloom filter size later. This could be of use, potentially implemented with a hyperloglog to get cardinality of an object. Ultimately I think w'ere best off trying to lower the storage space first.   
