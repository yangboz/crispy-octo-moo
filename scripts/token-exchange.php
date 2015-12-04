<?php
// edit these two lines
define(API_KEY, '');
define(API_SECRET, '');
if (!extension_loaded('oauth')) {
                print "This example requires the pecl/oauth extension: http://pecl.php.net/package/oauth\n";
                print "$ pecl install oauth\n";
                exit;
}
if ($_SERVER['REQUEST_METHOD'] != POST) {
?>
<html>
<head>
  <title>LinkedIn JavaScript API Token Exchange Code Sample</title>

  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>          

  <script type="text/javascript" src="http://platform.linkedin.com/in.js">
    api_key: <?php echo API_KEY . "\n"; // PHP strips the newline to the right ?>
    credentials_cookie: true
  </script>

  <script type="text/javascript">
    // Once we have an authorization, exchange the token and display fetched data to confirm it works
    function onLinkedInAuth() {

        // Make a client side call
        IN.API.Profile("me")
            .method("GET")
            .fields("id", "first-name", "last-name", "headline")
            .result( function(r) {
                console.log(r);
                var user = r.values[0];
                var msg = user.firstName + " " + user.lastName + " is " + user.headline + ".";
                console.log(msg);
                $('#client-side').html("Client-side: " + msg);
            }
        );

        // Exchange the token and make a server side call
        // looks like an empty string makes us call this page
        $.post('', function(msg) {
            $('#server-side').html("Server-side: " + msg);
        });
    }
  </script>
</head>
<body>
  <script type="in/login" data-onAuth="onLinkedInAuth"></script>
  <div id="client-side"></div>
  <div id="server-side"></div>
</body>
</html>
<?php
} else {
    $consumer_key = API_KEY;
    $consumer_secret = API_SECRET;
    $access_token_url = 'https://api.linkedin.com/uas/oauth/accessToken';
    
    // extract data from cookie stored in json
    $cookie_name = "linkedin_oauth_${consumer_key}";
    $credentials_json = $_COOKIE[$cookie_name];
    print $credentials_json;
    $credentials = json_decode($credentials_json);
    
    // validate signature
    if ($credentials->signature_version == 1) {
        if ($credentials->signature_order && is_array($credentials->signature_order)) {
            $base_string = '';
            // build base string from values ordered by signature_order
            foreach ($credentials->signature_order as $key) {
                if (isset($credentials->$key)) {
                    $base_string .= $credentials->$key;
                } else {
                    print "missing signature parameter: $key";
                }
            }
            // hex encode an HMAC-SHA1 string
            $signature =  base64_encode(hash_hmac('sha1', $base_string, $consumer_secret, true));
            
            // check if our signature matches the cookie's
            if ($signature == $credentials->signature) {
                try {
                    // init the client
                    $oauth = new OAuth($consumer_key, $consumer_secret);
                    // $oauth->disableSSLChecks(); // only use for internal testing
                    $access_token = $credentials->access_token;
                    
                    // swap 2.0 token for 1.0a token and secret
                    $oauth->fetch('https://api.linkedin.com/uas/oauth/accessToken', array('xoauth_oauth2_access_token' => $access_token), OAUTH_HTTP_METHOD_POST);
                    // parse the query string we get in response
                    parse_str($oauth->getLastResponse(), $response);
                    
                    // Debug information
                    // print "OAuth 1.O Access Token = " . $response['oauth_token'] . "\n";
                    // print "OAuth 1.O Access Token Secret = " . $response['oauth_token_secret'] . "\n";
                    
                    // set the new token values for 1.0a requests
                    $oauth->setToken($response['oauth_token'],$response['oauth_token_secret']);
                    
                    // go to town
                    $url = 'http://api.linkedin.com/v1/people/~:(id,first-name,last-name,headline)';
                    $oauth->fetch($url, array(), OAUTH_HTTP_METHOD_GET, array('x-li-format' => 'json')); // JSON!
                    $profile = json_decode($oauth->getLastResponse());
                    print "$profile->firstName $profile->lastName is $profile->headline.\n";
                } catch(OAuthException $e) {
                    print "OAuth request error";
                    print_r($oauth->debugInfo);
                    print $e;
                }
            } else {
                print "signature validation failed";    
            }
        } else {
            print "signature order missing";
        }
    } else {
        print "unknown cookie version";
    }
}
?>
