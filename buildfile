repositories.remote << 'http://repo1.maven.org/maven2/'

define 'in-place-multi-threaded-quicksort' do
	project.version = '0.1'
	test.with transitive('junit:junit:jar:4.10')
end