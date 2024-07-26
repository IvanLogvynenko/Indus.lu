class ACL:
    list = {}

    def __init__(self):
        self.list = {}

    def add_token(self, token):
        if token not in self.list.keys():
            self.list.add(token)
            self.list[token] = []
            return True
        else:
            return False

    def remove_token(self, token):
        if token in self.list.keys():
            self.list.remove(token)
            return True
        else:
            return False

    def check_token(self, token):
        if token in self.list.keys():
            return True
        else:
            return False

    # Assuming caller has checked for token validity before calling this method
    def check_permission(self, token, hash):
        if hash in self.list[token]:
            return True
        else:
            return False

    # Assuming caller has checked for token validity before calling this method
    def grant_permission(self, token, hash):
        if hash in self.list[token]:
            return False
        else:
            self.list[token].add(hash)
            return True
