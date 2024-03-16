# Definição de um recurso de execução local para fazer o push da imagem
resource "null_resource" "push_image_to_ecr" {
  provisioner "local-exec" {
    command = "aws ecr get-login-password --region ${var.AWS_REGION} | docker login --username AWS --password-stdin ${var.AWS_ACCOUNT_ID}.dkr.ecr.${var.AWS_REGION}.amazonaws.com && docker build -t ${var.AWS_ACCOUNT_ID}.dkr.ecr.${var.AWS_REGION}.amazonaws.com/ordering-system:latest ../ && docker push ${var.AWS_ACCOUNT_ID}.dkr.ecr.${var.AWS_REGION}.amazonaws.com/ordering-system:latest"
  }
}
