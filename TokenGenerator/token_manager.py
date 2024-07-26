import uuid
import threading
import time


class TokenManager:
    def __init__(self, expiration_time=3600):
        self.tokens = {}
        self.expiration_time = expiration_time

    def generate_token(self):
        token = str(uuid.uuid4())
        while token in self.tokens:
            token = str(uuid.uuid4())
        self.tokens[token] = time.time()
        # Schedule token deletion
        threading.Timer(self.expiration_time, self._expire_token, args=[token]).start()
        return token

    def _expire_token(self, token):
        if token in self.tokens:
            if time.time() - self.tokens[token] >= self.expiration_time:
                del self.tokens[token]

    def remove_token(self, token):
        if token in self.tokens:
            del self.tokens[token]
            return True
        else:
            return False

    def check_token(self, token):
        return token in self.tokens
