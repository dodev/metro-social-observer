LIBFOLDER="third-party-libs"

libs:
	rm -rf $(LIBFOLDER)
	wget -P tmp/ http://twitter4j.org/archive/twitter4j-4.0.1.zip
	unzip -jo -d $(LIBFOLDER) tmp/twitter4j-4.0.1.zip "lib/*"
	rm -rf tmp/

.PHONY: libs
