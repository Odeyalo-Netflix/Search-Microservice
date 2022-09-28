#!/bin/sh

docker run --name es-node01 --net elastic -e "discovery.type=single-node" -e "xpack.security.enabled=false" -p 9201:9200 -p 9300:9300 -t docker.elastic.co/elasticsearch/elasticsearch:7.6.2
