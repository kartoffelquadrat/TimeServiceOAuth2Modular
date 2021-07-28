# Dummy call owith fake token
echo 'Demonstrating unauthorized access using dummy token: "abc123"'
TOKEN="abc123"
echo -n 'curl -X GET http://127.0.0.1:8084/api/time?token='
echo $TOKEN
echo -n ' > Server Reply: ' 
curl -X GET http://127.0.0.1:8084/api/time?token=$TOKEN
echo
echo


# 1: Get token
echo 'Retrieving tokens from Token service using client credentials ("max", "acb123")'
echo 'curl -s -X POST --user timeservice-client:password-for-timeservice-client "http://127.0.0.1:8085/oauth/token?grant_type=password&username=max&password=abc123"'
TOKENREPLY=$(curl -s -X POST \
  --user timeservice-client:password-for-timeservice-client \
  "http://127.0.0.1:8085/oauth/token?grant_type=password&username=max&password=abc123")
echo -n " > Access_Token: "
TOKEN=$(echo $TOKENREPLY | jq -r ".access_token")
echo $TOKEN
echo -n " > Refresh_Token: "
echo $TOKENREPLY | jq -r ".refresh_token"
echo

echo 'Using access token to retrieve time from time-service'
echo -n 'curl -X GET http://127.0.0.1:8084/api/time?token='
echo $TOKEN
echo -n ' > Server Reply: ' 
curl -X GET http://127.0.0.1:8084/api/time?token=$TOKEN
echo
