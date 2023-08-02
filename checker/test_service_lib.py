from bs4 import BeautifulSoup
import requests

PORT = 5050


class CheckMachine:

    def __init__(self, checker):
        self.checker = checker

    def ping(self):
        r = requests.get(f'http://{self.checker.host}:{PORT}/AlienRegApp/register/', timeout=2)
        self.checker.check_response(r, 'Check failed')

    def put_flag(self, flag, vuln):
        new_id = None
        s = requests.Session()
        r = s.post(
            f'http://{self.checker.host}:{PORT}/AlienRegApp/register/',
            data={
                'id': flag
            },
            timeout=2,
        )
        if r.status_code == 302:
            r.status_code = 200
        self.checker.check_response(r, 'Could not put flag')
        data = BeautifulSoup(r.content.decode("utf-8"), 'html.parser')
        data = str(data.find_all('p')[1])
        new_id = data.split(": ")[1][: -4]
        print(new_id)

        return new_id

    def get_flag(self, flag_id, vuln):
        r = requests.get(
            f'http://{self.checker.host}:{PORT}/AlienRegApp/id/',
            cookies={
                'AlienID': flag_id,
            },
            timeout=2,
        )
        self.checker.check_response(r, 'Could not get flag')
        data = BeautifulSoup(r.content.decode("utf-8"), 'html.parser')
        data = str(data.find('p'))
        flag = data.split(": ")[1][: -4]
        
        return flag