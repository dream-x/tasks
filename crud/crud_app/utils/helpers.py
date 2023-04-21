import hashlib
import json
from typing import Dict


def prepare_hash(data: Dict) -> str:
    data = json.dumps(data).encode('utf-8')
    data_hash = hashlib.sha512(data).hexdigest()

    return data_hash
