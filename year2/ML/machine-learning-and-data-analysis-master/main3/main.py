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
dim = 784
X_train_ = X_train.reshape(len(X_train), dim)

pca = PCA(svd_solver="full")
pca = pca.fit(X_train_)

M=0

for arg, val in enumerate(np.cumsum(pca.explained_variance_ratio_)):
    if val > 0.68:
        M = arg + 1
        break

print(M)

X_train = X_train.reshape(len(X_train), dim)

pca = PCA(n_components=M, svd_solver='full')
pca = pca.fit(X_train)

X_train, X_test, y_train, y_test = train_test_split(X_train, y_train, test_size=0.3, random_state=12)
X_train = pca.transform(X_train)
X_test = pca.transform(X_test)

print(X_train.transpose()[0].mean())

random_farest = RandomForestClassifier(criterion='gini', min_samples_leaf=10, max_depth=20, n_estimators=10, random_state=12)
clf_random_forest = OneVsRestClassifier(random_farest).fit(X_train, y_train)
y_pred = clf_random_forest.predict(X_test)

CM = confusion_matrix(y_test, y_pred)
print(CM[7][7])

log_reg = LogisticRegression(solver='lbfgs', random_state=12)
clf_log_reg = OneVsRestClassifier(log_reg).fit(X_train, y_train)
y_pred = clf_log_reg.predict(X_test)

CM = confusion_matrix(y_test, y_pred)
print(CM[5][5])


tree = DecisionTreeClassifier(criterion='gini', min_samples_leaf=10, max_depth=20, random_state=12)
clf_tree = OneVsRestClassifier(tree).fit(X_train, y_train)
y_pred = clf_tree.predict(X_test)

CM = confusion_matrix(y_test, y_pred)
print(CM[1][1])

DATA = pd.read_csv("pred_for_task.csv", delimiter=',', index_col="FileName")
X_test = pd.DataFrame(DATA.drop(['Label'], axis = 1))
X_test = pca.transform(X_test)

y_pred = clf_random_forest.predict_proba(X_test)
idx = list(DATA.index).index("file9")
print(y_pred[idx][DATA['Label'][idx]])

y_pred = clf_log_reg.predict_proba(X_test)
idx = list(DATA.index).index("file24")
print(y_pred[idx][DATA['Label'][idx]])

y_pred = clf_tree.predict_proba(X_test)
idx = list(DATA.index).index("file9")
print(y_pred[idx][DATA['Label'][idx]])