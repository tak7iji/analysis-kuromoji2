Build plugin.

1. Copy buildSrc folder from Elasticsearch distribution.
2. Execute gradle.
  gradle -Dbuild.snapshot=false clean assemble
3. Copy artifact to Elasticsearch server.
4. Install plugin.
  elasticsearch-plugin install file:///path/to/plugin/analysis-kuromoji2-X.Y.Z.zip
