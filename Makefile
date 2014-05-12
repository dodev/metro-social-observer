LIBFOLDER="third-party-libs"

libs:
	rm -rf $(LIBFOLDER)
	wget -P tmp/ http://twitter4j.org/archive/twitter4j-4.0.1.zip
	wget -P tmp/ http://json-simple.googlecode.com/files/json-simple-1.1.1.jar
	unzip -jo -d $(LIBFOLDER) tmp/twitter4j-4.0.1.zip "lib/*"
	cp tmp/json-simple-1.1.1.jar $(LIBFOLDER)
	rm -rf tmp/

run:
	java metro-social-observer/bin/MetroSocialObserver

.PHONY: libs run
