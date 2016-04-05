#K-means prtitioning algorithm implementation on two data sets
##############################################################################
# Data set 1 (Wholesale customers data)
#Description: This dataset involves a number of distinct, though not usually 
#             independent, random variables. This dataset is well suited for 
#             performing classification and clustering algorithms on it.
#             There are 440 instances,8 attributes and no missing values
#             in this dataset
#############################################################################
#setwd("C:\\Users\\Kavalipurapu\\Desktop\\New folder\\project1_part2")
#read data from  CSV file
wcd_data<-read.csv("wcd.csv", header = TRUE, sep=",");
print(wcd_data)

##############################################################################
#Load and install packages
#install.packages("NbClust")
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


Number_of_clusters(wcd_data)

set.seed(1111)

clusters <- NbClust(wcd_data, min.nc=2, max.nc=15, method="kmeans")

table(clusters$Best.n[1,])

###Based on the number of clusters obtained, we perform kmeans algorithm
# Start the clock!
ptm <- proc.time()
kmeans <- kmeans(wcd_data, 3)
# Stop the clock
proc.time() - ptm
print(kmeans)
kmeans$size
kmeans$centers
plot(wcd_data,col=kmeans$cluster)
points(kmeans$centers,pch=32)

plot(wcd_data[c("Milk","Grocer")],col=kmeans$cluster,main="K-Mean Clustering")
text(wcd_data, labels=kmeans$cluster, col=kmeans$cluster)
points(kmeans$centers,pch=16)

##Calculating Time complexity for the dataset K-Means
# Start the clock!
ptm <- proc.time()
kmeans <- kmeans(wcd_data, 3,nstart=25)
# Stop the clock



