repositories.remote << 'http://repository.cloudera.com/artifactory/cloudera-repos/'
repositories.remote << 'http://repo1.maven.org/maven2'
repositories.remote << 'http://repository.sourcesense.com/nexus/content/repositories/public/'
repositories.remote << 'http://lresearch.net/maven2'

desc "Hive UDFs"
define "hive-udfs" do
    
  project.version = "0.4"
  compile.options.target = '1.6'
  deps = 'org.apache.hadoop.hive:hive-exec:jar:0.7.1-cdh3u3',
	'org.apache.hadoop:hadoop-core:jar:0.20.2-cdh3u3',
	'org.apache.tika:tika-core:jar:1.0',
	'org.apache.commons:commons-io:jar:1.3.2',
	'org.apache.lucene:lucene-analyzers:jar:3.6.1',
	'commons-logging:commons-logging:jar:1.0.3',
	'commons-codec:commons-codec:jar:1.6',
	'org.jsoup:jsoup:jar:1.6.1',
	_('lib/langdetect.jar'),
	_('lib/jsonic-1.2.0.jar')

  compile.with deps
  package :jar

  package(:tgz, :id => "#{name}-dist").path("#{name}-#{project.version}").tap do |distro|
    distro.path("lib").include artifacts(compile.dependencies)
    distro.path("lib").include package(:jar)
  end

end
