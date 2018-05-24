# BioGrakn
A Knowledge Graph-based Semantic Database for Biomedical Sciences

&nbsp;

## Introduction

*BioGrakn* is a graph-based semantic database that takes advantage of the power of knowledge graphs and machine reasoning to solve problems in the domain of biomedical science.

*Biograkn* has been built on top of [GRAKN.AI](http://grakn.ai/), a distributed knowledge graph database which allows complex data modeling, verification, scaling, querying, and analysis.

For further information, you can refer to the paper "[BioGrakn: A Knowledge Graph-based Semantic Database for Biomedical Sciences](https://link.springer.com/chapter/10.1007/978-3-319-61566-0_28)", presented at [CISIS-2017](http://voyager.ce.fit.ac.jp/conf/cisis/2017/), or to this [article on DZone](https://dzone.com/articles/a-knowledge-graph-based-semantic-database-for-biom).

&nbsp;

## Obtain the software

You can find a ready-to-use binary version of the software [here](https://github.com/xMAnton/BioGrakn/releases/tag/v1.2.0).

Choose a work directory and be sure to download the .jar and the two .gql files containg the ontology and the inference rules.

&nbsp;

## Data sources download 

*BioGrakn* is built by integrating data available from several databases, such as NCBI Entrez Gene, Gene Ontology, Uniprot Knowledge Base, Reactome, and others. 

For your convenience, all the used datasources have been collected and they are available for download from the URL [http://194.119.214.173/biograkn/](http://194.119.214.173/biograkn/).

Create a destination directory on your computer, e.g., `~/datasources`, and then copy the downloaded files into it, or alternatively, use *wget* this way:

```
 $ wget -A .bz2 -r -nd -nv -P ~/datasources http://194.119.214.173/biograkn/
```

&nbsp;

## Load the ontology and the inference rules

With GRAKN.AI up and running, load the ontology and the inference rules:

```
 $ cd [YOUR-GRAKN-1.2.0-DIR]
 $ ./graql console -k biograkn -f [WORKDIR]/ontology.gql
 $ ./graql console -k biograkn -f [WORKDIR]/rules.gql
 $ cd [WORKDIR]
```

Note that you can use an ad-hoc keyspace, such as *biograkn* above. 

