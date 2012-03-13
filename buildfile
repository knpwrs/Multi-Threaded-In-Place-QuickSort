repositories.remote << 'http://repo1.maven.org/maven2/'

define 'in-place-multi-threaded-quicksort' do
	project.version = '0.1'
	compile.using :source=>'1.6', :lint=>'all'
	test.with transitive('junit:junit:jar:4.10')
  test.using :java_args => ['-Xms1536m', '-Xmx1536m']
end
