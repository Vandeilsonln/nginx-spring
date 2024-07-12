POD_NAME=$(kubectl get pods -l app=db -o custom-columns=:metadata.name)
SCRIPT_FILENAME=init.sql

kubectl cp ./$SCRIPT_FILENAME $POD_NAME:/$SCRIPT_FILENAME

kubectl exec -it $POD_NAME -- /bin/bash -c "psql -U admin -d nginx-app -f /$SCRIPT_FILENAME"
