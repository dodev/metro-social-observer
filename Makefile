LIBFOLDER="third-party-libs"

get-libs:
	rm -rf $(LIBFOLDER)
	wget -P $(LIBFOLDER) http://twitter4j.org/archive/twitter4j-4.0.1.zip
