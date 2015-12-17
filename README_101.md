http://pan.baidu.com/s/1o6kuATG
	doc for VC
pan.baidu.com/s/1qWms6c8
	doc for DEV

https://github.com/yangboz/crispy-octo-moo
https://developers.facebook.com/apps/759417430835351/dashboard/
	snap415 facebook app

http://ec2-54-218-63-45.us-west-2.compute.amazonaws.com/IonicWebApp/www/#/tab/dash
	Amazon AWS for IonicWebApp

install mavern
	http://projects.spring.io/spring-social/
		http://spring.io/guides/gs/spring-boot/
			http://maven.apache.org/download.cgi

follow Spring-boot/README.md to build and run service and play with it
	must run from Spring-boot/ dir:  ~/Documents/src/crispy-octo-moo-master/Spring-boot/
	mvn spring-boot:run -Dspring.profiles.active=local
		/System/Library/Frameworks/JavaVM.framework/Versions/Current/Commands/java -classpath /Users/wyu/app/apache-maven-3.3.9/boot/plexus-classworlds-2.5.2.jar -Dclassworlds.conf=/Users/wyu/app/apache-maven-3.3.9/bin/m2.conf -Dmaven.home=/Users/wyu/app/apache-maven-3.3.9 -Dmaven.multiModuleProjectDirectory=/Users/wyu/doc/src/crispy-octo-moo-master/Spring-boot org.codehaus.plexus.classworlds.launcher.Launcher spring-boot:run -Dspring.profiles.active=local spring-boot:run -Dspring.profiles.active=local


follow http://ionicframework.com/docs/guide/installation.html to install ionic
	if npm "command not found", follow below to install npm:
		http://howtonode.org/introduction-to-npm
			curl http://npmjs.org/install.sh | sh
			-> doesn't work! "301 Moved Permanently"
		https://nodejs.org/en/
			this works (install npm)!
	install cordova
	install ionic
		on linux, failed, "sh: node: command not found"
		on mac, OK

build and run ionic client
	cd ~/Documents/src/crispy-octo-moo-master/IonicWebApp/
		ionic serve
			(then your browser should open a new tab with the Facebook login window shown)

enable Spring development and debugging in IntelliJ
	https://www.jetbrains.com/idea/help/spring.html
		I only followed the steps to ensure Spring Support is enabled - did nothing else
	After open the root folder in IntelliJ, got "Project SDK not installed" - click to install
		then, intelli-sense works!
			BUT, starting spring from cmdline, can attach debugger in IntelliJ, but breakpoint does not trigger!

start Spring from IntelliJ:
	Pre: IntelliJ Run EditConfig already has SpringBoot debug configuration
	Pre: IntelliJ should build fine
	1. IntelliJ Run Run SpringBoot-debug: fails with "Unable to parse command line options: Unrecognized option: --spring.profiles.active=local"
	2. IntelliJ Run EditConfig SpringBoot-debug:
		remove from ProgramArguments "--spring.profiles.active=local"
		set ActiveProfile to empty (was "local")
	3. IntelliJ Run Run SpringBoot-debug: fails with "Unable to parse command line options: Unrecognized option: --spring.output.ansi.enabled=always"
		??


install MongoDB
	https://docs.mongodb.org/manual/tutorial/install-mongodb-on-ubuntu/
	https://docs.mongodb.org/manual/tutorial/install-mongodb-on-red-hat/
	https://docs.mongodb.org/manual/tutorial/install-mongodb-on-os-x/
		brew update
			failed, even with sudo, but seems benign
		brew install mongodb
			Warning: It appears you have MacPorts or Fink installed.
				https://git.io/brew-troubleshooting
		mongod --dbpath /export/content/data/mongodb
			- this starts the MondoDB service on local machine
	MongoDB 101: http://code.tutsplus.com/articles/mapping-relational-databases-and-sql-to-mongodb--net-35650
		"mongo --host 172.29.121.142" - connect to (remote) mongodb
			help
			db.createCollection("tblPost")
				similar to: Create SQL Table
			db.tblPost.insert({user_name:"mark", post_text:"This is a sample post", post_privacy:"public", post_likes_count:0})
				similar to: SQL INSERT INTO tblPost (...)
			db.tblPost.find()
				similar to: SQL SELECT * FROM tblPost
			db.tblPost.find({user_name:"mark"})
				similar to: SQL SELECT * FROM tblPost WHERE user_name='mark'
			db.tblPost.find({},{post_text:1,post_likes_count:1})
				similar to: SQL SELECT post_text, post_likes_count FROM tblPost
			db.tblPost.find({user_name:"mark"},{post_text:1,post_likes_count:1})
				similar to: SQL SELECT post_text, post_likes_count FROM tblPost WHERE user_name='mark'
			db.tblPost.find({user_name:"mark",post_privacy:"public"},{post_text:1,post_likes_count:1})
				similar to: SQL SELECT post_text, post_likes_count FROM tblPost WHERE user_name='mark' AND post_privacy='public'
			db.tblPost.find({$or:[{user_name:"mark"},{post_privacy:"public"}]},{post_text:1,post_likes_count:1})
				similar to: SQL SELECT post_text, post_likes_count FROM tblPost WHERE user_name='mark' OR  post_privacy='public'
			db.tblPost.find({user_name:"mark"}).sort({post_likes_count:1})
				similar to: SQL SELECT *  FROM tblPost WHERE user_name = 'mark' ORDER BY post_likes_count ASC
			db.tblPost.find({user_name:"mark"}).sort({post_likes_count:-1})
				similar to: SQL SELECT *  FROM tblPost WHERE user_name = 'mark' ORDER BY post_likes_count DESC
			db.tblPost.update({user_name:"mark"},{$set:{post_privacy:"private"}},{multi:true})
				similar to: SQL UPDATE tblPost SET post_privacy = "private" WHERE user_name='mark'
			db.tblPost.remove({user_name:"mark"})
				similar to: SQL DELETE FROM tblPost WHERE user_name='mark'
			db.tblPost.ensureIndex({user_name:1,post_likes_count:-1})
				similar to: SQL CREATE INDEX index_posts ON tblPost(user_name,post_likes_count DESC)
			db.tblPost.getIndexes()
				similar to: SQL SHOW INDEX FROM tblPost


https://www.jetbrains.com/idea/help/run-debug-configuration-spring-boot.html
	IntelliJ Run/Debug Configuration: Spring Boot


https://developers.facebook.com/docs/apis-and-sdks#third-party-sdks
	Facebook Graph API and Ads API (java versions: spring-social-facebook; restfb; kinvey)
https://github.com/spring-projects/spring-social-facebook
http://www.kinvey.com/facebook-open-graph-for-mobile-apps
	paid
http://restfb.com/
	needs ant
	needs to get your own Facebook access token
		https://developers.facebook.com/tools/explorer

http://ant.apache.org/bindownload.cgi
http://ant.apache.org/manual/index.html

What else to do after updating *.pom dependency so IntelliJ can find the jar?
	http://stackoverflow.com/questions/28642013/how-to-refresh-lib-folder-when-update-pom-xml
		does not help
	instead, don't bother updating *.pom; instead, IntelliJ - File - ProjectStructure - ProjectSettings - Modules - + - Library - FromMaven...

http://docs.spring.io/spring-social/docs/1.1.4.RELEASE/reference/htmlsingle/
	Spring Social Reference, including OAuth

https://developers.facebook.com/docs/facebook-login/access-tokens/expiration-and-extension
	Convert 2-hour facebook access token to 60-day token

http://www.mkyong.com/spring/quick-start-maven-spring-example/
	Maven + Spring hello world example
