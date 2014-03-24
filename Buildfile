repositories.remote << 'http://repository.cloudera.com/artifactory/cloudera-repos'
repositories.remote << 'http://repo1.maven.org/maven2'
repositories.remote << 'http://repository.sourcesense.com/nexus/content/repositories/public'
repositories.remote << 'http://lresearch.net/maven2'

desc "Hive UDFs"
define "hive-udfs" do
    
  project.version = "1.0.2"
  compile.options.target = '1.6'

  HADOOP = ['org.apache.hive:hive-exec:jar:0.10.0-cdh4.6.0',
	    'org.apache.hadoop:hadoop-common:jar:2.0.0-cdh4.6.0']

  RUNTIME = ['org.apache.tika:tika-core:jar:1.0',
	'org.apache.commons:commons-io:jar:1.3.2',
	'org.apache.lucene:lucene-analyzers:jar:3.6.1',
	'commons-logging:commons-logging:jar:1.0.3',
	'commons-codec:commons-codec:jar:1.6',
	'org.jsoup:jsoup:jar:1.6.1',
	'nl.bitwalker:UserAgentUtils:jar:1.6',
	_('lib/langdetect.jar'),
	_('lib/jsonic-1.2.0.jar')]

  download(artifact('nl.bitwalker:UserAgentUtils:jar:1.6')=>'http://java.net/projects/user-agent-utils/downloads/download/UserAgentUtils-1.6.jar')

  compile.with HADOOP,RUNTIME
  package(:jar).merge(RUNTIME)

end
