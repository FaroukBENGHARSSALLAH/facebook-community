# facebook-community
Facebook Developer Circles Community Challenge 2018 source code

This folder contains the website's source code.

   You can visit the live version via [facebook-community](https://facebook-community.herokuapp.com/).

###### APIs used
Facebook API used
- Facebook Login

Dev Technologies
-  SpringBoot
-  Spring social facebook
-  JPA/Hibernate
-  Spring social facebook
-  Java8
-  Maven
-  Google Maps
-  Spring themeleaf

     You can visit the live version via [facebook-community](https://facebook-community.herokuapp.com/).
     
You can uses these test users

```
email : user_ybrgayz_one@tfbnw.net    password : user_one
email : user_xiuyqeh_two@tfbnw.net   password : user_two
email : user_ofnzwrt_three@tfbnw.net    password : user_three
email : user_jiubsgv_four@tfbnw.net   password : user_four
email : user_ouzlphr_five@tfbnw.net    password : user_five
```


###### import and build

 You have to create a [facebook developer account](https://developers.facebook.com/), it will provide your app ID and secret, that will be used in `application.properties file`. Then, add the facebook login API.

      > The project is a spring boot application
  
  > Build in localhost
  
1. import the project
2. update `application.properties file` with your own tokens provided by Your Facebook App
3. build using maven from the prject root directory 
```
maven clean install
```

4. copy the war file from the target folder to your server webapp folder, like Tomcat server
5. open this link in the web browser `http://localhost:8081/`
6. Test the website, you can add test users from the facebook app

   > Build to deploy in the cloud
   
1. add your domain name in the facebook App's domains list
2. configure your redirect URL, it has to be in this format `https://YOUR DOMAIN NAME/facebook` in the facebook APP
3. uncomment the line `45` in `FacebookController.java` in the folder `src/main/java/com/facebook/community/controller/` and set the   `ApplicationUrl` to redirect URL 
6. Test the website, you can use the test users added in the facebook app
