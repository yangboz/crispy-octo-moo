###Cordova:

    sudo cordova -d plugin add /Volumes/Transcend/github/phonegap-facebook-plugin --variable APP_ID="759417430835351" --variable APP_NAME="snap415"

###iOS

    <key>LSApplicationQueriesSchemes</key>
    <array>
    <string>fbauth</string>
    </array>
    <key>NSAppTransportSecurity</key>
    <dict>
    <key>NSAllowsArbitraryLoads</key>
    <true/>
    </dict>

###Android

    <?xml version='1.0' encoding='utf-8'?>
    <resources>
    <string name="fb_app_id">759417430835351</string>
    <string name="fb_app_name">snap415</string>
    </resources>


