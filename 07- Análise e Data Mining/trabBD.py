import numpy as np
#import pandas as pd
import matplotlib.pyplot as pl
#import seaborn as sb
import psycopg2.extras
from xml.etree.ElementTree import Element, SubElement, tostring, Comment
from xml.dom import minidom

#---------------- INICIO DA CONEXAO COM A BASE DE DADOS------------------------#
print("Conectando ao banco de dados...")

conn = psycopg2.connect("dbname='1802Viludani' user='1802Viludani' host='200.134.10.32' password='578769'")
cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)
#---------------- FIM DA CONEXAO COM A BASE DE DADOS------------------------#

#---------------- INICIO DA AQUISICAO DE DADOS------------------------#
cur.execute("SELECT * FROM gostamfilme")

linhas = cur.fetchall()
#login generoid
cur.execute("select distinct login FROM gostamfilme")

qtlogin = cur.fetchall()
valorqt = len(qtlogin)
#print valorqt

cur.execute("select distinct generoid FROM gostamfilme")
qtgenero = cur.fetchall()
valorgenero = len(qtgenero)
#print valorgenero

#---------------- FIM DA AQUISICAO DE DADOS------------------------#

#---------------- INICIO DA CRIACAO DA MATRIZ PARA CLUSTER---------------------#

matriz = np.zeros((valorqt, valorgenero), dtype=np.float64)

cur.execute("SELECT * FROM gostamfilme")

nomes = []
valorP = 0
auxP = 'DI1802alexandrematias'
nomes.append(auxP)

for row in cur.fetchall():
  atual = row[0]

  if atual != auxP:
    auxP = atual
    valorP = valorP + 1
    nomes.append(auxP)

  if row[1] == 'Horror':
    matriz[valorP][0] = matriz[valorP][0]+1
  elif row[1] == 'Crime':
    matriz[valorP][1] = matriz[valorP][1]+1
  elif row[1] == 'Drama':
    matriz[valorP][2] = matriz[valorP][2]+1
  elif row[1] == 'Adventure':
    matriz[valorP][3] = matriz[valorP][3]+1
  elif row[1] == 'Action':
    matriz[valorP][4] = matriz[valorP][4]+1
  elif row[1] == 'Mystery':
    matriz[valorP][5] = matriz[valorP][5]+1
  elif row[1] == 'Thriller':
    matriz[valorP][6] = matriz[valorP][6]+1
  elif row[1] == 'Sci-Fi':
    matriz[valorP][7] = matriz[valorP][7]+1
  elif row[1] == 'Animation':
    matriz[valorP][8] = matriz[valorP][8]+1
  elif row[1] == 'Comedy':
    matriz[valorP][9] = matriz[valorP][9]+1
  elif row[1] == 'War':
    matriz[valorP][10] = matriz[valorP][10]+1

#print matriz
#---------------- FIM DA CRIACAO DA MATRIZ PARA CLUSTER---------------------#

#---------------- INICIO DO KMEANS---------------------#

from sklearn.cluster import KMeans

kmeans = KMeans(n_clusters = 6, random_state = 0)

kmeans.fit(matriz)

resp = kmeans.labels_
#print(resp)

#---------------- FIM DO KMEANS---------------------#

#--------------INICIO DA ANALISE ---------------------#

cluster1=[]
cluster2=[]
cluster3=[]
cluster4=[]
cluster5=[]
cluster6=[]

cur.execute("SELECT distinct login FROM gostamfilme order by login asc")
rr = cur.fetchall()

somador = 0

for x2 in resp:
    if(x2 == 0):
        cluster1.append(rr[somador])
    elif(x2 == 1):
        cluster2.append(rr[somador])
    elif(x2 == 2):
        cluster3.append(rr[somador])
    elif(x2 == 3):
        cluster4.append(rr[somador])
    elif(x2 == 4):
        cluster5.append(rr[somador])
    elif(x2 == 5):
        cluster6.append(rr[somador])
    somador = somador + 1
#--------------INICIO DA ANALISE ---------------------#

#---------------- INICIO DA AQUISICAO DE DADOS------------------------#
cur.execute("SELECT * from resp3")

linhas = cur.fetchall()
#login generoid
cur.execute("select distinct login FROM resp3")

qtlogin = cur.fetchall()
valorqt = len(qtlogin)
#print valorqt

cur.execute("select distinct genero FROM resp3")
qtgenero = cur.fetchall()
#for row2 in qtgenero:
#    print row2
#    print
valorgenero = len(qtgenero)
#print valorgenero

#---------------- FIM DA AQUISICAO DE DADOS------------------------#

#---------------- INICIO DA CRIACAO DA MATRIZ PARA CLUSTER---------------------#

matriz = np.zeros((valorqt, valorgenero), dtype=np.float64)

cur.execute("SELECT login, genero from resp3")

nomes = []
valorP = 0
auxP = 'DI1802alexandrematias'
nomes.append(auxP)

for row in cur.fetchall():
  atual = row[0]

  if atual != auxP:
    auxP = atual
    valorP = valorP + 1
    nomes.append(auxP)

  if row[1] == 'rock':
    matriz[valorP][0] = matriz[valorP][0]+1
  elif row[1] == 'metal':
    matriz[valorP][1] = matriz[valorP][1]+1
  elif row[1] == 'pop':
    matriz[valorP][2] = matriz[valorP][2]+1
  elif row[1] == 'hip hop':
    matriz[valorP][3] = matriz[valorP][3]+1
  elif row[1] == 'eletronica':
    matriz[valorP][4] = matriz[valorP][4]+1
  elif row[1] == 'Melodic hardcore':
    matriz[valorP][5] = matriz[valorP][5]+1
  elif row[1] == 'French house':
    matriz[valorP][6] = matriz[valorP][6]+1
  elif row[1] == 'R&amp;B':
    matriz[valorP][7] = matriz[valorP][7]+1
  elif row[1] == 'rap':
    matriz[valorP][8] = matriz[valorP][8]+1
  elif row[1] == 'reggae':
    matriz[valorP][9] = matriz[valorP][9]+1
  elif row[1] == 'Contemporary Christian music':
    matriz[valorP][10] = matriz[valorP][10]+1
  elif row[1] == 'Neo soul':
    matriz[valorP][11] = matriz[valorP][11]+1
  elif row[1] == 'Neue Deutsche H\xc3\xa4rte':
    matriz[valorP][12] = matriz[valorP][12]+1
  elif row[1] == 'Soul':
    matriz[valorP][13] = matriz[valorP][13]+1
  elif row[1] == 'Post-punk':
    matriz[valorP][14] = matriz[valorP][14]+1
  elif row[1] == 'Pagode':
    matriz[valorP][15] = matriz[valorP][15]+1
  elif row[1] == 'Praise &amp; worship':
    matriz[valorP][16] = matriz[valorP][16]+1
  elif row[1] == 'Psychedelic trance':
    matriz[valorP][17] = matriz[valorP][17]+1
  elif row[1] == 'experimental':
    matriz[valorP][18] = matriz[valorP][18]+1
  elif row[1] == 'Singer-songwriter':
    matriz[valorP][19] = matriz[valorP][19]+1
  elif row[1] == 'House Music':
    matriz[valorP][20] = matriz[valorP][20]+1
  elif row[1] == 'Big beat':
    matriz[valorP][21] = matriz[valorP][21]+1
  elif row[1] == 'Shanty':
    matriz[valorP][22] = matriz[valorP][22]+1
  elif row[1] == 'Synthwave':
    matriz[valorP][23] = matriz[valorP][23]+1
  elif row[1] == 'Country':
    matriz[valorP][24] = matriz[valorP][24]+1
  elif row[1] == 'Americana':
    matriz[valorP][25] = matriz[valorP][25]+1

#---------------- FIM DA CRIACAO DA MATRIZ PARA CLUSTER---------------------#

#---------------- INICIO DO KMEANS---------------------#

from sklearn.cluster import KMeans

kmeans = KMeans(n_clusters = 6  , random_state = 0)

kmeans.fit(matriz)

resp = kmeans.labels_
#print(resp)

#---------------- FIM DO KMEANS---------------------#

#--------------INICIO DA ANALISE ---------------------#

cluster21=[]
cluster22=[]
cluster23=[]
cluster24=[]
cluster25=[]
cluster26=[]

cur.execute("SELECT distinct login FROM resp3 order by login asc")
rr = cur.fetchall()

somador = 0

for x2 in resp:
    if(x2 == 0):
        cluster21.append(rr[somador])
    elif(x2 == 1):
        cluster22.append(rr[somador])
    elif(x2 == 2):
        cluster23.append(rr[somador])
    elif(x2 == 3):
        cluster24.append(rr[somador])
    elif(x2 == 4):
        cluster25.append(rr[somador])
    elif(x2 == 5):
        cluster26.append(rr[somador])
    somador = somador + 1

#--------------INICIO DA ANALISE ---------------------#
#--------------VER QUAL CLUSTER E MAIS PARECIDO-------#

clusterParecido21 = []
clusterParecido22 = []
clusterParecido23 = []
clusterParecido24 = []
clusterParecido25 = []
clusterParecido26 = []

clusters = [cluster1, cluster2, cluster3, cluster4, cluster5, cluster6]
clusters2 = [cluster21, cluster22, cluster23, cluster24, cluster25, cluster26]
similares = [];
similaresv = [];

valorParecido = 0;
for a in [0,1,2,3,4,5]:
    for i in clusters[a]:
        for j in cluster21:
            if(i == j):
                valorParecido = valorParecido + 1
    clusterParecido21.append(valorParecido)
    valorParecido = 0

n_max = max(clusterParecido21)
n_pos = clusterParecido21.index(n_max)
similaresv.append(n_max)
similares.append(n_pos)

valorParecido = 0;
for a in [0,1,2,3,4,5]:
    for i in clusters[a]:
        for j in cluster22:
            if(i == j):
                valorParecido = valorParecido + 1
    clusterParecido22.append(valorParecido)
    valorParecido = 0

n_max = max(clusterParecido22)
n_pos = clusterParecido22.index(n_max)
similaresv.append(n_max)
similares.append(n_pos)

valorParecido = 0;
for a in [0,1,2,3,4,5]:
    for i in clusters[a]:
        for j in cluster23:
            if(i == j):
                valorParecido = valorParecido + 1
    clusterParecido23.append(valorParecido)
    valorParecido = 0

n_max = max(clusterParecido23)
n_pos = clusterParecido23.index(n_max)
similaresv.append(n_max)
similares.append(n_pos)

valorParecido = 0;
for a in [0,1,2,3,4,5]:
    for i in clusters[a]:
        for j in cluster24:
            if(i == j):
                valorParecido = valorParecido + 1
    clusterParecido24.append(valorParecido)
    valorParecido = 0

n_max = max(clusterParecido24)
n_pos = clusterParecido24.index(n_max)
similaresv.append(n_max)
similares.append(n_pos)

valorParecido = 0;
for a in [0,1,2,3,4,5]:
    for i in clusters[a]:
        for j in cluster25:
            if(i == j):
                valorParecido = valorParecido + 1
    clusterParecido25.append(valorParecido)
    valorParecido = 0

n_max = max(clusterParecido25)
n_pos = clusterParecido25.index(n_max)
similaresv.append(n_max)
similares.append(n_pos)

valorParecido = 0;
for a in [0,1,2,3,4,5]:
    for i in clusters[a]:
        for j in cluster26:
            if(i == j):
                valorParecido = valorParecido + 1
    clusterParecido26.append(valorParecido)
    valorParecido = 0

n_max = max(clusterParecido26)
n_pos = clusterParecido26.index(n_max)
similaresv.append(n_max)
similares.append(n_pos)

print(similares)
print(similaresv)

#----------------------------FIM CLUSTERS PARECIDOS ---------------#

#--------------INICIO DOS GRAFICO BASES----------------------#

width_n = 0.5
y_axis = [len(cluster1), len(cluster2), len(cluster3), len(cluster4), len(cluster5), len(cluster6)]
x_axis = np.arange(len(y_axis))
pl.xlabel('Clusters')
#pl.xticks([r + width_n for r in range(len(y_axis))], ['Cluster 1', 'Cluster 2', 'Cluster 3', 'Cluster 4', 'Cluster 5', 'Cluster 6'])
pl.ylabel('Quantidade de Pessoas')
pl.title('Cluster Filme')
pl.bar(x_axis, y_axis, width=width_n, color='blue', label='Filmes')
pl.legend()
pl.show()


width_n = 0.5
y_axis2 = [len(cluster25), len(cluster26), len(cluster21), len(cluster24), len(cluster23), len(cluster22)]
x_axis2 = np.arange(len(y_axis2))
pl.xlabel('Clusters')
#pl.xticks([r + width_n for r in range(len(y_axis))], ['Cluster 1', 'Cluster 2', 'Cluster 3', 'Cluster 4', 'Cluster 5', 'Cluster 6'])
pl.ylabel('Quantidade de Pessoas')
pl.title('Cluster Musicas')
pl.bar(x_axis2, y_axis2, width=width_n, color='Orange', label='Musicas')
pl.legend()
pl.show()

#--------------FIM DO GRAFICO BASES-------------------------#



#--------------INICIO DO GRAFICO----------------------#

width_n = 0.3
y_axis = [len(cluster1), len(cluster2), len(cluster3), len(cluster4), len(cluster5), len(cluster6)]
x_axis = np.arange(len(y_axis))
y_axis2 = [len(cluster25), len(cluster26), len(cluster21), len(cluster24), len(cluster23), len(cluster22)]
x_axis2 = [x + width_n for x in x_axis]
x_similares = [x + width_n for x in x_axis2]

pl.xlabel('Clusters mas similares')
#pl.xticks([r + width_n for r in range(len(y_axis))], ['Cluster 1', 'Cluster 2', 'Cluster 3', 'Cluster 4', 'Cluster 5', 'Cluster 6'])
pl.ylabel('Quantidade de Pessoas')
pl.title('Comparativo de Clusters(Gosto Musical/Filmes)')

pl.bar(x_axis, y_axis, width=width_n, color='blue', label='Filmes')
pl.bar(x_axis2, y_axis2, width=width_n, color='orange', label='Musicas', align='center')
pl.bar(x_similares, similares, width=width_n, color='green', label='similares', align='center')

pl.legend()
pl.show()

#--------------FIM DO GRAFICO-------------------------#

