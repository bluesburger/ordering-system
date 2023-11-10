@echo off
setlocal

for /L %%i in (1,1,10000) do (
    curl "http://localhost:31080/api/v1/order/all/PREPARO_INICIADO"
)

endlocal