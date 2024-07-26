from enum import Enum


class Actions(Enum):
    VIEW = 1
    REMOVE = 2
    # UPDATE = 3


class ACL:
    list = {}

    def __init__(self):
        self.list = {}

    def add_token(self, token):
        if token not in self.list.keys():
            self.list.add(token)
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
    def check_permission(self, token, action):
        if action in self.list[token]:
            return True
        else:
            return False

    # Assuming caller has checked for token validity before calling this method
    def grant_permission(self, token, action):
        if action in self.list[token]:
            return True
        else:
            self.list[token].add(action)
            return True
