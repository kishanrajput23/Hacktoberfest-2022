# CREATING A RESPONSIVE WEBSITE 

### 1. Create a basic HTML file
- The HTML5 embraces simple and easily understandable doctype. Moreover, it also eradicates the usage of self-closing tags, and introduced several new elements, including <menu>, <video> and <audio>, to name a few. It eventually saves a lot of web development time and efforts. Here is the complete code that you need to include in your HTML file.

- Code Snippet of HTML file:

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Responsive Blogs Using HTML5 and CSS3</title>
  <meta name="description" content="">
  <meta name="author" content="">
  <meta name="viewport" content="width=device-width; initial-scale=1.0">
  <link rel="shortcut icon" href="/favicon.ico">
  <link rel="apple-touch-icon" href="/apple-touch-icon.png">
  <link rel="stylesheet" href="css/style.css" />
  </head>
  <body>
<div id="wrapper">
  <header id="header">
    <h1>This is a Responsive Blogs Using HTML5 and CSS3</h1>
    <nav class="navigation">
      <ul>
        <li><a href="#">Home</a></li>
        <li><a href="#">Services</a></li>
        <li><a href="#">Blog</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
    </nav>
  </header>
    <div id="content" class="clearfix">
      <section id="left-sidebar">
        <p>Left Sidebar Content Goes Here</p>
      </section>
             
      <section id="right">
        <p>Right Side Content Goes Here</p>
      </section>
    </div>
         
    <footer>
      <p>Some copyright and legal notices gere. May be use the © symbol a bit.</p>
    </footer>
  </div>
</body>
</html>
  
### 2. Working on various sections of Page
- In the above mentioned code snippet, I have included five HTML tags that can help you define your web page. The entire page is wrapped via a div tag, and the wrapper ID will wrap on the complete website.

- Broadly, there are three different sections on a page, including header, content div and the footer. They have been defined in the code by their respective tags. The ‘nav’ tag has been also used to define the navigation elements. Through the section tag, the content section and sidebar are defined. In order to make them float, CSS style needs to be considered.

### 3. Styling via CSS3
- To enhance the basic layout created via the HTML in step 1 and create a refined look and feel, CSS3 can be used for styling in a required way. Here I have worked on CSS to create pretty effects.

- Code Snippet to create ‘navigation link hover’ effect:
#wrapper{
 width:1024px; 
 margin:0 auto;
}
#header h1{
  text-align: center;
}
  nav.navigation {
  display: block;
  margin-bottom: 10px;
}
nav.navigation  ul {
  list-style: none;
  font-size: 14px; 
  text-align: center;
}
nav.navigation  ul li {
  display: inline-block;
}
nav.navigation  ul li a {
  display: block;
  float: left;
  padding: 3px 6px;
  color: #575c7d;
  font-size:22px;
  text-decoration: none;
  font-weight: bold;
}
nav.navigation  ul li a:hover {
  background: #ccc;
  color: #fff;
  -webkit-border-radius: 3px;
  -moz-border-radius: 3px;
  border-radius: 3px;
  padding: 3px 6px;
  margin: 0;
  text-decoration: none;
}
By including chink of code in your CSS document, you can create some cool and neat navigation link hover effect.

Code Snippet to create ‘clearfix styles’ effect:
/* page Content */
#content {
  display: block;
  clear: both;
  margin-bottom: 20px;
  min-height: 500px;
}

#left-sidebar {
  width: 35%;
  float: left;
  margin: 0 15px;
  background: #ccc; 
  min-height: 500px; 
  text-align: center;
}
#right {
  float: right;
  width: 62%;
  background: #efefef; 
  text-align: center;
  min-height: 500px;
}
footer{text-align: center;}
/* clearfix */
.clearfix:after {
  content: “.”;
  display: block;
  clear: both;
  visibility: hidden;
  line-height: 0;
  height: 0;
}
.clearfix {
  display: inline-block;
}

html[xmlns] .clearfix {
  display: block;
}
* html .clearfix {
  height: 1%;
}
-The clearfix styles can ensure a standard-compliant blog. The aforementioned code includes #left-sidebar and #right elements that represent the respective side of the section to the page. The div#content container consists the clearfix class. This will help float the web content in an optimal way while cleaning up the additional space.

### 4. Creating a Responsive Blog using HTML5 and CSS3
- For creating a responsive blog, one can use the attributes of the targeted device. However, there is a better solution that can be used by implementing a CSS framework like the Bootstrap.

- Bootstrap offers a brilliant way to create a responsive blog that can run efficiently on various screen sizes.

- Code Snippet of Responsive Blog using HTML5 and CSS3:

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>

<body>
<div class="container">
<div class="jumbotron">

<header>
  <h1>Welcome to My Blog </h1>
  <p>Check the responsive blog page!</p>
</div>

<div class="row">
  <div class="col-md-4">
    <h3>First Blog Post</h3>
    <p>Blog Content Goes Here</p>
  </div>
  <div class="col-md-4">
    <h3>Second Blog Post</h3>
    <p>Blog Content Goes Here</p>
  </div>
  <div class="col-md-4">
    <h3>Third Blog Post</h3>
    <p>Blog Content Goes Here</p>
  </div>
</div>
<footer id="footer">
<p>copyright text </p>
 </footer>
 </div>

</div>
</body>
</html>
-To ensure that your blog can be easily accessed from various devices, the viewport meta tag can be used. This meta tag basically defines the way your blog will be represented on a device.

### with help of this you can create a responsive website...
