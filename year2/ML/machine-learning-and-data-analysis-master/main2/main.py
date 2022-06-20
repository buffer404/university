from sklearn.tree import DecisionTreeClassifier
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.multiclass import OneVsRestClassifier
from sklearn.metrics import confusion_matrix
from sklearn.decomposition import PCA
import pandas as pd
import numpy as np
from keras.datasets import mnist
import warnings
warnings.filterwarnings("ignore")


(X_train, y_train), (X_pred, y_pred) = mnist.load_data()
dim = 784  # 28*28
X_train_ = X_train.reshape(len(X_train), dim)

pca = PCA(svd_solver='full')
pca = pca.fit(X_train_)

M = 0
for arg, val in enumerate(np.cumsum(pca.explained_variance_ratio_)):
    if val > 0.71:                                                              # <----- МЕНЯТЬ ПО ЗАДАНИЮ!
        M = arg + 1
        break

print(M)

X_train = X_train.reshape(len(X_train), dim)

# Поиск счёт
pca = PCA(n_components=M, svd_solver='full')
pca = pca.fit(X_train)

# Разделение выборки
X_train, X_test, y_train, y_test = train_test_split(X_train, y_train, test_size=0.3, random_state=54)  # <----- МЕНЯТЬ ПО ЗАДАНИЮ!
X_train = pca.transform(X_train)
X_test = pca.transform(X_test)

print(X_train.transpose()[0].mean())