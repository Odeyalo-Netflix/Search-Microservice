#!/bin/sh

url=$1
index_name=$2
curl $url/$index_name  -X PUT -H "Content-Type:application/json" -d "{
  \"mappings\": {
      \"properties\": {
        \"name\": {
          \"type\": \"text\"
        }, \"name_suggester\": {
            \"type\":\"completion\"
        },
        \"year\": {
          \"type\": \"keyword\"
        }
      }
  }
}"
