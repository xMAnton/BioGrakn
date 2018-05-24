# BioGrakn
A Knowledge Graph-based Semantic Database for Biomedical Sciences

&nbsp;

## Introduction

*BioGrakn* is a graph-based semantic database that takes advantage of the power of knowledge graphs and machine reasoning to solve problems in the domain of biomedical science.

*Biograkn* has been built on top of [GRAKN.AI](http://grakn.ai/), a distributed knowledge graph database which allows complex data modeling, verification, scaling, querying, and analysis.

For further information, you can refer to the paper "[BioGrakn: A Knowledge Graph-based Semantic Database for Biomedical Sciences](https://link.springer.com/chapter/10.1007/978-3-319-61566-0_28)", presented at [CISIS-2017](http://voyager.ce.fit.ac.jp/conf/cisis/2017/), or to this [article on DZone](https://dzone.com/articles/a-knowledge-graph-based-semantic-database-for-biom).

&nbsp;

## Data sources download 

*BioGrakn* is built by integrating data available from several databases, such as NCBI Entrez Gene, Gene Ontology, the Uniprot Knowledge Base, Reactome, and others. 

For your convenience, all the used datasources have been collected and they are available for download from the URL [http://194.119.214.173/biograkn/](http://194.119.214.173/biograkn/).

Create a work directory on your computer, e.g., `~/datasources`, and then copy the downloaded files into it, or alternatively, use *wget* this way:

```
 $ wget -A .bz2 -r -nd -nv -P ~/datasources http://194.119.214.173/biograkn/
```

