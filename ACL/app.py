from flask import Flask
from ACL import ACL

app = Flask(__name__)
acl = ACL()


@app.route('/add_token/<token>', methods=['POST'])
def add_token(token):
    return {'valid': acl.add_token(token)}, 200


@app.route('remove_token/<token>', methods=['DELETE'])
def remove_token(token):
    if acl.remove_token(token):
        return 200
    else:
        return 404


@app.route('check_access/<token>/<hash>', methods=['GET'])
def check_access(token, hash):
    if acl.check_token(token):
        return {'valid': acl.check_permission(token, hash)}, 200
    else:
        return 404


@app.route('grant_access/<token>/<hash>', methods=['PUT'])
def grant_access(token, hash):
    if acl.check_token(token):
        return {'valid': acl.grant_permission(token, hash)}, 200
    else:
        return 404
