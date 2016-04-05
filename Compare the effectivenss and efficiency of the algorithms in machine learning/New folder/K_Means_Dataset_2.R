#K-means prtitioning algorithm implementation on two data sets
##############################################################################
# Data set 2 (User Knowledge Modeling Data Set )
#Description: This dataset involves a number of distinct, though not usually 
#             independent, random variables. This dataset is well suited for 
#             performing classification and clustering algorithms on it.
#             There are 403 instances,5 attributes and no missing values
#             in this dataset
#############################################################################

#read data from  CSV file
wcd_data1<-read.csv("dumd.csv", header = TRUE, sep=",");
print(wcd_data1)

##############################################################################
#Load and install packages
install.packages("NbClust")
library(NbClust)

###############################################################################
#Kmeans algorithm for the data set

#Determine the number of clusters to be used in K-means
Number_of_clusters <- function(data, count=15, seed=1111){
  number <- (nrow(data)-1)*sum(apply(data,2,var))
  for (i in 2:count){
    set.seed(seed)
    number[i] <- sum(kmeans(data, centers=i)$withinss)}
  plot(1:count, number, type="b", xlab="Number of Clusters",
       ylab="Within groups sum of squares")}


Number_of_clusters(wcd_data1)

set.seed(1111)

clusters <- NbClust(wcd_data1, min.nc=2, max.nc=15, method="kmeans")

table(clusters$Best.n[1,])

###Based on the number of clusters obtained, we perform kmeans algorithm
ptm <- proc.time()
kmeans <- kmeans(wcd_data, 2)
# Stop the clock
proc.time() - ptm
print(kmeans)
kmeans$size
kmeans$centers
plot(wcd_data1,col=kmeans$cluster)
points(kmeans$centers,pch=32)

plot(wcd_data1[c("STG","STR")],col=kmeans$cluster,main="K-Mean Clustering")
points(kmeans$centers,pch=16)
text(wcd_data1, labels=kmeans$cluster, col=kmeans$cluster)


##Calculating Time complexity 
# Start the clock!
ptm <- proc.time()
kmeans <- kmeans(wcd_data1, 3,nstart=25)
# Stop the clock

