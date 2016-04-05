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
h_wcd_data<-read.csv("C:\\Users\\Kavalipurapu\\Desktop\\New folder\\wcd.csv", header = TRUE, sep=",");
View(h_wcd_data)
h_wcd_data$species<-NULL

##############################################################################
#Load and install packages

#install.packages("cluster")
library(cluster)
library(graphics)
#remove.packages("stats")
#install.packages("stats")
library(stats)
###############################################################################

dm<- dist(as.matrix(h_wcd_data))
print(dm)

diana <- diana(dm, metric = "euclidean", stand = TRUE,diss=FALSE)
a<-as.dendrogram(diana)
plot(a)
b<-cut(a,10)
plot(b$upper)


print(diana)
plot(diana)
plot(diana,FALSE,NULL, main="DIANA Algorithm using Euclidean distance", nmax.lab=30, max.strlen=20)
a<-as.dendogram(ag)
cut.tree<-cutree(diana, k = 3, h = 10)
a<-as.dendogram(cut.tree)
plot(cut.tree)

system.time(diana(dm, metric = "euclidean", stand = TRUE,diss=FALSE))



## Hierarchical Clustering algorithm implementation on two data sets
##############################################################################
#Data set 2 (Wholesale customers data)
#Description: This dataset involves a number of distinct, though not usually 
#             independent, random variables. This dataset is well suited for 
#             performing classification and clustering algorithms on it.
#             There are 440 instances,8 attributes and no missing values
#             in this dataset
#############################################################################
#read data from  CSV file
h_dumd_data<-read.csv("C:\\Users\\Kavalipurapu\\Desktop\\New folder\\q.csv", header = TRUE, sep=",");
View(h_dumd_data)
h_dumd_data$UNS<-NULL
h_dumd_data$X<-NULL
h_dumd_data$X.1<-NULL
h_dumd_data$X.2<-NULL


##############################################################################
#Load and install packages

install.packages("cluster")
library(cluster)
library(graphics)
###############################################################################

library(cluster)
library(graphics)
h_dm<- dist(as.matrix(h_dumd_data))
h_dm
dv <- diana(dm, metric = "euclidean", stand = TRUE,diss=FALSE)
print(dv)
plot(dv,FALSE,NULL, main="DIANA Algorithm using Euclidean distance", nmax.lab=30, max.strlen=20)
