import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import DistanceMetric
from sklearn.neighbors import KNeighborsClassifier

if __name__ == '__main__':

    DATA = pd.read_csv(
        "https://courses.openedu.ru/assets/courseware/v1/431a30c4a3a2b3f265f8cce1a809aa0f/asset-v1:ITMOUniversity+MLDATAN+spring_2022_ITMO_bac+type@asset+block/pulsar_stars_new.csv",
        decimal='.', delimiter=",")
    pulsar_stars = DATA[(DATA.MIP >= 10) & (DATA.MIP <= 100)]

    print(pulsar_stars)

    print(f"Число строк в выборке: {len(pulsar_stars)}")
    print(f"Среднее для столбца MIP: {pulsar_stars.MIP.mean()}")
    print(f"Максимум для столбца SIP: {pulsar_stars.SIP.max()}\n")

    X = pulsar_stars.drop(["TG"], axis=1).values
    y = pulsar_stars["TG"].values
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=2022)
    scaler = StandardScaler().fit(X_train)
    data_scaler_train = scaler.transform(X_train)
    data_scaler_test = scaler.transform(X_test)

    print(f"MAX SIP в тенировочном наборе: {data_scaler_train.transpose()[3].max()}")
    print(f"MAX SIP в тестовом наборе: {data_scaler_test.transpose()[3].max()}\n")

    reg_train = LogisticRegression(random_state=2022, solver='lbfgs').fit(data_scaler_train, y_train)
    reg_test = LogisticRegression(random_state=2022, solver='lbfgs').fit(data_scaler_test, y_test)
    print(f"Коэффициент при MIP, если тренировочный набор: {reg_train.coef_[0][0]}")
    print(f"Коэффициент при MIP, если тестовый набор: {reg_test.coef_[0][0]}\n")

    print(f"Accuracy на тестовых данных, если тренировочный набор: {reg_train.score(data_scaler_test, y_test)}")
    print(f"Accuracy на тестовых данных, если тестовый набор: {reg_test.score(data_scaler_test, y_test)}\n")

    STAR = [136.750000, 57.178449, -0.068415, -0.636238, 3.642977, 20.959280, 6.896499, 53.593661]
    STAR = scaler.transform([STAR])
    predict = reg_train.predict(STAR)[0]
    print(f"Ввероятность отнесения звезды к назначенному классу: {reg_train.predict_proba(STAR)[0][predict]}")

    neigh = KNeighborsClassifier(n_neighbors=5, p=2) # p=1 - manhattan, p=2 - euclidean
    neigh.fit(data_scaler_train, y_train)

    print(f"При k = {5} относится к классу: {neigh.predict(STAR)[0]}\n")

    for m in ["euclidean", "manhattan"]:
        dist = DistanceMetric.get_metric(m)
        distances = [dist.pairwise(np.concatenate(([i], STAR)))[0][1] for i in list(data_scaler_train)]
        print("Расстояние до ближайшего по метрике \"" + m + "\":", min(distances))