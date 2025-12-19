npm run build
rm -rf ../backend/src/main/resources/static/*
cp -r dist/* ../backend/src/main/resources/static/
echo "Frontend deployed to backend/src/main/resources/static/"