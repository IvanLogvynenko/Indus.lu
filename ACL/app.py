from flask import Flask, request
from ACL import ACL, Actions

app = Flask(__name__)
acl = ACL()


# in the following lines 1 stands for true and
@app.route('/add_token/<token>', methods=['POST'])
def add_token():
    token = request.get_json().get('token')
    if acl.add_token(token):
        return True, 200
    else:
        return False, 400


@app.route('remove_token/<token>', methods=['DELETE'])
def remove_token():
    token = request.get_json().get('token')
    if (acl.remove_token(token)):
        return True, 200
    else:
        return False, 404


@app.route('check_access/<token>/<action>', methods=['GET'])
def check_access():
    data = request.get_json()
    token = data.get('token')
    action = Actions(data.get('action'))

    if acl.check_token(token):
        if acl.check_permission(token, action):
            return True, 200
        else:
            return False, 403
    else:
        return False, 404


@app.route('grant_access/<token>/<action>', methods=['PUT'])
def grant_access():
    data = request.get_json()
    token = data.get('token')
    action = Actions(data.get('action'))

    if acl.check_token(token):
        if acl.grant_permission(token, action):
            return True, 200
        else:
            return False, 403
    else:
        return False, 404
