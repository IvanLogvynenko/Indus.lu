from flask import Flask, request
import token_manager

app = Flask(__name__)
tm = token_manager.TokenManager()


@app.route('/get_new_token', methods=['GET'])
def get_new_token():
    return {'token': tm.generate_token()}, 200


@app.route('/remove_token/<token>', methods=['DELETE'])
def remove_token():
    token = request.get_json().get('token')
    if tm.remove_token(token):
        return {'valid': True}, 200
    else:
        return {'valid': False}, 404


@app.route('/check_token/<token>', methods=['GET'])
def check_token(token):
    return {'valid': tm.check_token(token)}, 200
