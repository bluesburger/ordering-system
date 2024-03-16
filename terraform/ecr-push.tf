# Definição de um recurso de execução local para fazer o push da imagem
resource "null_resource" "push_image_to_ecr" {
  provisioner "local-exec" {
    command = "aws ecr get-login-password --region ${var.TF_VAR_AWS_REGION} | docker login --username AWS --password-stdin ${var.TF_VAR_AWS_ACCOUNT_ID}.dkr.ecr.${var.TF_VAR_AWS_REGION}.amazonaws.com && docker build -t ${var.TF_VAR_AWS_ACCOUNT_ID}.dkr.ecr.${var.TF_VAR_AWS_REGION}.amazonaws.com/ordering-system:latest ../ && docker push ${var.TF_VAR_AWS_ACCOUNT_ID}.dkr.ecr.${var.TF_VAR_AWS_REGION}.amazonaws.com/ordering-system:latest"
  }
}
