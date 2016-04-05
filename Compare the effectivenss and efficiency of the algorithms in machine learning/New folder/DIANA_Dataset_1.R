##############################################################################
## Hierarchical Clustering algorithm implementation on two data sets
##############################################################################
# Data set 1 (Wholesale customers data)
#Description: This dataset involves a number of distinct, though not usually 
#             independent, random variables. This dataset is well suited for 
#             performing classification and clustering algorithms on it.
#             There are 440 instances,8 attributes and no missing values
#             in this dataset
#############################################################################
#read data from  CSV file
h_wcd_data<-read.csv("wcd.csv", header = TRUE, sep=",");
View(h_wcd_data)
h_wcd_data$species<-NULL
##############################################################################
#Load and install packages

install.packages("cluster")
library(cluster)
###############################################################################

dm<- dist(as.matrix(h_wcd_data))
print(dm)

diana <- diana(dm, metric = "euclidean", stand = TRUE,diss=FALSE)
print(diana,main="Original_tree")
a<-as.dendrogram(diana)
b<-cut(a,2000)
frame<-par(mfrow=c(1,2))
plot(a,main="original",sub="Dendrogram of the dataset")
plot(b$lower[[1]],main="After Cut",sub="dendrogram after cut")

##Calculating Time complexity 
# Start the clock!
ptm <- proc.time()
time_complexity_diana<-diana(dm, metric = "euclidean", stand = TRUE,diss=FALSE)
# Stop the clock
proc.time()
