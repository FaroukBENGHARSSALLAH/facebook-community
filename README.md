# facebook-community
Facebook Developer Circles Community Challenge 2018 source code

This folder contains the website's source code. The project provides a panel to regroupe supporters arround an issue, using Facebook account as a signature for membership

###### Features
- [x] login page
- [x] welcome and message page
- [x] Supporters message with thier position in a Map
- [ ] Supporters mail list display
- [ ] Supporters gender list display
- [ ] Supporters data dashbord

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
     
You can use these test users

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
  
- import the project
- update `application.properties file` with your own tokens provided by Your Facebook App
- build using maven from the prject root directory 
```
maven clean install
```

- copy the war file from the target folder to your server webapp folder, like Tomcat server
- open this link in the web browser `http://localhost:8080/`
- Test the website, you can add test users from the facebook app
- Verify data integrity in the database by visiting the H2 web console from this link `http://localhost:8080/database`

   > Build to deploy in the cloud
   
- add your domain name in the facebook App's domains list, be sure it uses the SSL certificate or create your certificate
- configure your redirect URL, it has to be in this format `https://YOUR DOMAIN NAME/facebook` in the facebook APP
. uncomment the line `45` in `FacebookController.java` in the folder `src/main/java/com/facebook/community/controller/` and set the   `ApplicationUrl` to redirect URL 
- Test the website, you can use the test users added in the facebook app
- Verify data integrity in the database by visiting H2 web console from this link `https://YOUR DOMAIN NAME/database`
